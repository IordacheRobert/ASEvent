package ro.robert.licenta.events.dao;

import org.apache.log4j.Logger;

import com.sun.jersey.spi.resource.Singleton;

import ro.robert.licenta.events.model.Item;


@Singleton
public class ItemRepositoryImpl extends BaseRepositoryImpl<Item> implements ItemRepository{

    public ItemRepositoryImpl() {
		super(Item.class);
	}

	private static final Logger LOG = Logger.getLogger(ItemRepositoryImpl.class);

}
