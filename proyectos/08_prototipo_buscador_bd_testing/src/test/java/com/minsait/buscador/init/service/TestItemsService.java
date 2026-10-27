package com.minsait.buscador.init.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.minsait.buscador.init.entities.ItemEntity;
import com.minsait.buscador.init.mappers.MapeadorObjetos;
import com.minsait.buscador.init.model.Item;
import com.minsait.buscador.init.repository.ItemsRepository;
import com.minsait.buscador.init.service.impl.ItemsServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TestItemsService {
	@Mock
    ItemsRepository itemsRepository;

    @Mock
    MapeadorObjetos itemMapper;

    @InjectMocks
    ItemsServiceImpl itemsService;
    
    @Test
    void testNuevoItem() {

        ItemEntity item = new ItemEntity(0, "unique-url", "testing", "desc");
        Item dto = new Item("unique-url", "testing", "desc");

        when(itemsRepository.findFirstByUrl("unique-url"))
                .thenReturn(Optional.empty());

        when(itemMapper.dtoToEntity(dto))
                .thenReturn(item);

        when(itemsRepository.save(any(ItemEntity.class)))
                .thenReturn(item);

        when(itemMapper.entityToDto(item))
                .thenReturn(dto);

        Optional<Item> res = itemsService.alta(dto);

        assertThat(res).isPresent();
        assertThat(res.get()).isEqualTo(dto);

        verify(itemsRepository).findFirstByUrl("unique-url");
        verify(itemMapper).dtoToEntity(dto);
        verify(itemsRepository).save(item);
    }
}
