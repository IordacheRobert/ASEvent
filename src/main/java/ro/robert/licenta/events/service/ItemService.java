package ro.robert.licenta.events.service;


import java.util.List;
import java.util.Map;

import ro.robert.licenta.events.model.Item;

//bussiness logic
public interface ItemService {

    List<Item> getListFromNamedParams(Map<String, Object> params);

    Item saveOrUpdate(Item item);

    Item getSingleFromNamedParams(Map<String, Object> params);

    Integer delete(String deleteById, Long id);

}
