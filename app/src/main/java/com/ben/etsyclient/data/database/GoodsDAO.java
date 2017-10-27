package com.ben.etsyclient.data.database;

import com.ben.etsyclient.data.model.goods.Goods;
import com.ben.etsyclient.data.model.goods.GoodsList;

public interface GoodsDAO extends DAO {

    long saveGoods(Goods goods);

    long deleteGoods(long id);

    GoodsList getGoodsList();
}
