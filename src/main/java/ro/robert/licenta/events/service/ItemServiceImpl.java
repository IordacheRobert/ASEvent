package ro.robert.licenta.events.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sun.jersey.api.core.InjectParam;
import com.sun.jersey.spi.resource.Singleton;

import ro.robert.licenta.events.dao.ItemRepository;
import ro.robert.licenta.events.dao.ItemRepositoryImpl;
import ro.robert.licenta.events.model.Item;


@Singleton
public class ItemServiceImpl implements ItemService {

    private static final Logger LOG = Logger.getLogger(ItemServiceImpl.class);

    private ItemRepository itemRepository;

    @InjectParam
    public void setItemRepository(ItemRepositoryImpl itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getListFromNamedParams(Map<String, Object> params) {
        // Some other business
        return itemRepository.getListFromNamedParams(params);
    }

    public Item saveOrUpdate(Item item) {
        // Some other business
        return itemRepository.saveOrUpdate(item);
    }

    public Item getSingleFromNamedParams(Map<String, Object> params) {
        // Some other business
        return itemRepository.getSingleValue(params);
    }

    public Integer delete(String deleteById, Long id) {
        // Some other business
        return itemRepository.delete(deleteById, id);
    }
}