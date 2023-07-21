package team5.todo.repository;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

import javax.sql.DataSource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import team5.todo.annotation.RepositoryTest;
import team5.todo.domain.Category;

@RepositoryTest
public class CategoryRepositoryTest {

	private final CategoryRepository categoryRepository;

	@Autowired
	public CategoryRepositoryTest(DataSource dataSource) {
		this.categoryRepository = new CategoryRepository(dataSource);
	}

	@Test
	@DisplayName("전체 카테고리를 조회하면,id 오름차 순으로 3개의 카테고리를 반환한다.")
	void findAllTest() {
		//given

		//when
		List<Category> actual = categoryRepository.findAll();

		//then
		assertThat(actual.size()).isEqualTo(3);
		assertThat(actual.get(0).getId()).isEqualTo(1L);
		assertThat(actual.get(1).getId()).isEqualTo(2L);
		assertThat(actual.get(2).getId()).isEqualTo(3L);
	}
}
