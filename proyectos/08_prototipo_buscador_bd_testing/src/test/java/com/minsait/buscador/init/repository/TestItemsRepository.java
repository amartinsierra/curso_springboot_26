package com.minsait.buscador.init.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.minsait.buscador.init.entities.ItemEntity;

@DataJpaTest
public class TestItemsRepository {
	  @Autowired
	  ItemsRepository itemsRepository;
	  @Test
	  void testFindByTematica() {
	    itemsRepository.deleteAll();
	    itemsRepository.save(new ItemEntity("url1", "java", "desc1"));
	    itemsRepository.save(new ItemEntity("url2", "java", "desc2"));
	    itemsRepository.save(new ItemEntity("url3", "spring", "desc3"));
	    List<ItemEntity> javaItems = itemsRepository.findByTematica("java");
	    List<ItemEntity> pythonItems = itemsRepository.findByTematica("python");
	    assertThat(javaItems).hasSize(2)
	      .extracting(ItemEntity::getUrl)
	      .containsExactlyInAnyOrder("url1", "url2");
	    assertThat(pythonItems).hasSize(0);
	  }
	  @Test
	  void testFindFirstByUrl() {
	    itemsRepository.deleteAll();
	    itemsRepository.save(new ItemEntity( "unique-url", "testing", "desc"));
	    ItemEntity found = itemsRepository.findFirstByUrl("unique-url").orElse(null);
	    assertThat(found).isNotNull();
	    assertThat(found.getTematica()).isEqualTo("testing");
	  }

}
