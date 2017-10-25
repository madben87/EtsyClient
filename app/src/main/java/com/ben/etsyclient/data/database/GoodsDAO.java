package com.ben.etsyclient.data.database;

import com.ben.etsyclient.model.goods.Goods;
import com.ben.etsyclient.model.goods.GoodsList;

public interface GoodsDAO extends DAO {

    long saveGoods(Goods goods);

    GoodsList getGoodsList();
}
