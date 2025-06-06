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

	/**
	 * 価格の安い順に並べ替える
	 * @return 価格の安い順に並べ替えられた商品リスト
	 */
	List<Item> findAllByOrderByPrice();

	/**
	 * 商品名のキーワード検索
	 * @return 商品名にキーワードが含まれた商品の商品リスト
	 */
	List<Item> findByNameContains(String keyword);

	/**
	 * 商品名のキーワードと価格上限値を指定した検索
	 * @param keyword  商品名のキーワード
	 * @param maxPrice 価格上限値
	 * @return 商品リスト
	 */
	List<Item> findByNameContainsAndPriceLessThanEqual(String keyword, Integer maxPrice);

}
