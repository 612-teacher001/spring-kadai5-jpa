package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	/**
	 * カテゴリ検索
	 * @param  categoryId 検索対象となるカテゴリコード
	 * @return 指定されたカテゴリに含まれる商品の商品リスト
	 */
	List<Item> findByCategoryId(int categoryId);

}
