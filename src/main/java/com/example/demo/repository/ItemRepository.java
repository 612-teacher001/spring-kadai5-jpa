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

	/**
	 * 価格上限以下の商品検索
	 * @param  maxPrice 検索対象となる商品の価格の上限値
	 * @return 価格上限値以下の価格の商品の商品リスト
	 */
	List<Item> findByPriceLessThanEqual(Integer maxPrice);

}
