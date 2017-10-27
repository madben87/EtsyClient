package com.ben.etsyclient.view.result;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ben.etsyclient.data.DataManager;
import com.ben.etsyclient.data.Repository;
import com.ben.etsyclient.data.model.goods.GoodsList;
import com.ben.etsyclient.data.model.goods.Pagination;
import com.ben.etsyclient.data.model.goods.Params;
import com.ben.etsyclient.util.Constants;
import com.ben.etsyclient.util.MadLog;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

@Singleton
public class ResultSearchPresenterImpl implements ResultSearchPresenter, Constants {

    private Repository dataManager;
    private CompositeSubscription compositeSubscription;
    private ResultSearchView view;
    private int firstVisibleItems, visibleItemCount, totalItemCount;
    private boolean loading;
    private GoodsList goodsList;

    @Inject
    public ResultSearchPresenterImpl(DataManager dataManager) {this.dataManager = dataManager;}

    @Override
    public void refreshList(String category, String keywords) {
        Subscription subscription = dataManager.syncItems(category, keywords)
                .subscribe(new Observer<GoodsList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        MadLog.log(this, e.getMessage());
                    }

                    @Override
                    public void onNext(GoodsList goodsList) {
                        view.showResult(goodsList);
                    }
                });
        compositeSubscription.add(subscription);
    }

    public Observable<GoodsList> loadNextPage(Params params, Pagination pagination) {
        return dataManager.loadNextItems(params.getCategory(), params.getKeywords(),
                pagination.getEffectiveLimit(), pagination.getNextOffset());
    }

    @Override
    public void setPagination(RecyclerView recyclerView, final GoodsList list) {
        final GridLayoutManager layoutManager;

        layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();

        goodsList = list;

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    if (dy > 0) {

                        visibleItemCount = layoutManager.getChildCount();
                        totalItemCount = layoutManager.getItemCount();
                        firstVisibleItems = layoutManager.findFirstVisibleItemPosition();

                        if (!loading && (visibleItemCount + firstVisibleItems) >= totalItemCount
                                && goodsList.getPagination().getNextOffset() != 0) {

                            loading = true;

                            MadLog.log(this, "onScrolled position = " + firstVisibleItems);

                            compositeSubscription.add(loadNextPage(goodsList.getParams(), goodsList.getPagination())
                                    .subscribe(new Observer<GoodsList>() {
                                        @Override
                                        public void onCompleted() {
                                            loading = false;
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            MadLog.log(this, e.getMessage());
                                        }

                                        @Override
                                        public void onNext(GoodsList list) {
                                            goodsList = list;
                                            view.showNextPage(goodsList.getResults());
                                        }
                                    }));
                        }
                    }
                }
            });
    }

    @Override
    public void attachView(ResultSearchView mvpView) {
        view = mvpView;
        compositeSubscription = new CompositeSubscription();
        MadLog.log(this, "attachView");
    }

    @Override
    public void detachView() {
        view = null;
        unSubscribe();
        MadLog.log(this, "detachView");
    }

    private void unSubscribe() {
        if (compositeSubscription != null) {
            if (!compositeSubscription.isUnsubscribed()) {
                compositeSubscription.unsubscribe();
                compositeSubscription = null;
            }
        }
    }
}
