package com.ben.etsyclient.data.database;

import com.ben.etsyclient.model.goods.Goods;
import com.ben.etsyclient.model.goods.GoodsList;

public interface GoodsDAO extends DAO {

    long addGoods(Goods goods);

    void cacheGoods(Goods goods);

    GoodsList getGoodsList();
}
