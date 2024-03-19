package ru.otus.reactive.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactiveServiceApplication {
	/*
	 TODO Домашнее задание:
	   - Переработать ProductDetailsController таким образом, чтобы он возвращал DetailedProductDto, который
	     собирается из продукта из базы и ответа от сервиса деталей. DetailedProductDto должен включать в себя поля
	     id, name, description. Следует учесть что в сервисе деталей может не оказаться записи, соответствующей
	     продукту, тогда description останется пустым (тут придется чуть расширить работу со статус кодом 404)
	   - В ProductDetailsController должна быть возможность запросить детальные продукты:
	      - по конкретному id,
	      - по набору id,
	      - все что есть в БД данного сервиса
	   - Примечание: детали платежа в сервисе деталей просто генерируются на ходу, не обязательно добавлять БД.
	     Чтобы сымитировать в таком случае отсутствие записи, может добавить какое-нибудь условие на id (например,
	     не будут находиться все детали для продуктов, у который id четный).
	 */

	public static void main(String[] args) {
		SpringApplication.run(ReactiveServiceApplication.class, args);
	}
}
