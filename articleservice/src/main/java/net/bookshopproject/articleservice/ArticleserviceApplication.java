package net.bookshopproject.articleservice;

import net.bookshopproject.articleservice.model.Article;
import net.bookshopproject.articleservice.model.Comment;
import net.bookshopproject.articleservice.model.User;
import net.bookshopproject.articleservice.repository.ArticleRepository;
import net.bookshopproject.articleservice.repository.CommentRepository;
import net.bookshopproject.articleservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class ArticleserviceApplication implements CommandLineRunner {

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ArticleRepository articleRepository;

	public static void main(String[] args) {
		SpringApplication.run(ArticleserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user1 = new User(1L,"Emil","Nowak");
		User user2 = new User(2L,"Adam","Wójcik");
		User user3 = new User(3L,"Agata","Sikora");
		User user4 = new User(4L,"Marcin","Leszczyński");
		User user5 = new User(5L,"Damian","Kowalski");
		User user6 = new User(6L,"Zofia","Koło");

		Article article1 = new Article(1L,user2,"Okazja","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sit amet laoreet enim. Nulla eget leo nec libero dapibus faucibus. In vel orci lacus. Mauris tellus tellus, sollicitudin tempus porttitor vel, tempus nec est. Cras suscipit, elit nec viverra lobortis, tellus urna ornare sem, a volutpat velit est in metus. Duis et ultrices neque, non rhoncus lectus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Cras gravida leo a sem aliquet, a pulvinar lorem malesuada. Pellentesque quis lorem urna. Nunc non magna est. Nunc consectetur in purus fringilla bibendum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Morbi fermentum felis id turpis finibus elementum non in orci. Cras commodo justo eros, porttitor imperdiet lectus consequat in.", LocalDate.of(2024,1,14));
		Article article2 = new Article(2L,user2,"Wyprzedaż","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sit amet laoreet enim. Nulla eget leo nec libero dapibus faucibus. In vel orci lacus. Mauris tellus tellus, sollicitudin tempus porttitor vel, tempus nec est. Cras suscipit, elit nec viverra lobortis, tellus urna ornare sem, a volutpat velit est in metus. Duis et ultrices neque, non rhoncus lectus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Cras gravida leo a sem aliquet, a pulvinar lorem malesuada. Pellentesque quis lorem urna. Nunc non magna est. Nunc consectetur in purus fringilla bibendum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Morbi fermentum felis id turpis finibus elementum non in orci. Cras commodo justo eros, porttitor imperdiet lectus consequat in.",LocalDate.of(2024,2,1));
		Article article3 = new Article(3L,user2,"Prezent","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sit amet laoreet enim. Nulla eget leo nec libero dapibus faucibus. In vel orci lacus. Mauris tellus tellus, sollicitudin tempus porttitor vel, tempus nec est. Cras suscipit, elit nec viverra lobortis, tellus urna ornare sem, a volutpat velit est in metus. Duis et ultrices neque, non rhoncus lectus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Cras gravida leo a sem aliquet, a pulvinar lorem malesuada. Pellentesque quis lorem urna. Nunc non magna est. Nunc consectetur in purus fringilla bibendum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Morbi fermentum felis id turpis finibus elementum non in orci. Cras commodo justo eros, porttitor imperdiet lectus consequat in.",LocalDate.of(2024,2,24));
		Article article4 = new Article(4L,user2,"Nowa oferta","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sit amet laoreet enim. Nulla eget leo nec libero dapibus faucibus. In vel orci lacus. Mauris tellus tellus, sollicitudin tempus porttitor vel, tempus nec est. Cras suscipit, elit nec viverra lobortis, tellus urna ornare sem, a volutpat velit est in metus. Duis et ultrices neque, non rhoncus lectus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Cras gravida leo a sem aliquet, a pulvinar lorem malesuada. Pellentesque quis lorem urna. Nunc non magna est. Nunc consectetur in purus fringilla bibendum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Morbi fermentum felis id turpis finibus elementum non in orci. Cras commodo justo eros, porttitor imperdiet lectus consequat in.",LocalDate.of(2024,3,11));
		Article article5 = new Article(5L,user2,"Tylko do końca miesiąca","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sit amet laoreet enim. Nulla eget leo nec libero dapibus faucibus. In vel orci lacus. Mauris tellus tellus, sollicitudin tempus porttitor vel, tempus nec est. Cras suscipit, elit nec viverra lobortis, tellus urna ornare sem, a volutpat velit est in metus. Duis et ultrices neque, non rhoncus lectus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Cras gravida leo a sem aliquet, a pulvinar lorem malesuada. Pellentesque quis lorem urna. Nunc non magna est. Nunc consectetur in purus fringilla bibendum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Morbi fermentum felis id turpis finibus elementum non in orci. Cras commodo justo eros, porttitor imperdiet lectus consequat in.",LocalDate.of(2024,3,30));
		Article article6 = new Article(6L,user2,"Nowa oferta","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sit amet laoreet enim. Nulla eget leo nec libero dapibus faucibus. In vel orci lacus. Mauris tellus tellus, sollicitudin tempus porttitor vel, tempus nec est. Cras suscipit, elit nec viverra lobortis, tellus urna ornare sem, a volutpat velit est in metus. Duis et ultrices neque, non rhoncus lectus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Cras gravida leo a sem aliquet, a pulvinar lorem malesuada. Pellentesque quis lorem urna. Nunc non magna est. Nunc consectetur in purus fringilla bibendum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Morbi fermentum felis id turpis finibus elementum non in orci. Cras commodo justo eros, porttitor imperdiet lectus consequat in.",LocalDate.of(2024,4,4));
		Article article7 = new Article(7L,user2,"Co nowego?","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sit amet laoreet enim. Nulla eget leo nec libero dapibus faucibus. In vel orci lacus. Mauris tellus tellus, sollicitudin tempus porttitor vel, tempus nec est. Cras suscipit, elit nec viverra lobortis, tellus urna ornare sem, a volutpat velit est in metus. Duis et ultrices neque, non rhoncus lectus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Cras gravida leo a sem aliquet, a pulvinar lorem malesuada. Pellentesque quis lorem urna. Nunc non magna est. Nunc consectetur in purus fringilla bibendum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Morbi fermentum felis id turpis finibus elementum non in orci. Cras commodo justo eros, porttitor imperdiet lectus consequat in.",LocalDate.of(2024,4,17));
		Article article8= new Article(8L,user2,"Nowa wyprzedaż","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sit amet laoreet enim. Nulla eget leo nec libero dapibus faucibus. In vel orci lacus. Mauris tellus tellus, sollicitudin tempus porttitor vel, tempus nec est. Cras suscipit, elit nec viverra lobortis, tellus urna ornare sem, a volutpat velit est in metus. Duis et ultrices neque, non rhoncus lectus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Cras gravida leo a sem aliquet, a pulvinar lorem malesuada. Pellentesque quis lorem urna. Nunc non magna est. Nunc consectetur in purus fringilla bibendum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Morbi fermentum felis id turpis finibus elementum non in orci. Cras commodo justo eros, porttitor imperdiet lectus consequat in.",LocalDate.of(2024,4,29));
		Article article9 = new Article(9L,user2,"Nowe nabytki","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sit amet laoreet enim. Nulla eget leo nec libero dapibus faucibus. In vel orci lacus. Mauris tellus tellus, sollicitudin tempus porttitor vel, tempus nec est. Cras suscipit, elit nec viverra lobortis, tellus urna ornare sem, a volutpat velit est in metus. Duis et ultrices neque, non rhoncus lectus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Cras gravida leo a sem aliquet, a pulvinar lorem malesuada. Pellentesque quis lorem urna. Nunc non magna est. Nunc consectetur in purus fringilla bibendum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Morbi fermentum felis id turpis finibus elementum non in orci. Cras commodo justo eros, porttitor imperdiet lectus consequat in.",LocalDate.of(2024,5,8));

		Comment comment1 = new Comment(1L,user1,article2,"test",LocalDate.of(2024,2,5));
		Comment comment2 = new Comment(2L,user3,article1,"hello",LocalDate.of(2024,2,7));
		Comment comment3 = new Comment(3L,user6,article6,"komentarz",LocalDate.of(2024,4,12));
		Comment comment4 = new Comment(4L,user4,article6,"zażółć gęślą jaźń",LocalDate.of(2024,4,13));
		Comment comment5 = new Comment(5L,user5,article6,"test123",LocalDate.of(2024,4,15));
		Comment comment6 = new Comment(6L,user1,article7,"qwerty",LocalDate.of(2024,4,21));
		Comment comment7 = new Comment(7L,user3,article9,"test",LocalDate.of(2024,5,9));
		Comment comment8 = new Comment(8L,user3,article9,"123",LocalDate.of(2024,5,11));

		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		userRepository.save(user4);
		userRepository.save(user5);
		userRepository.save(user6);

		articleRepository.save(article1);
		articleRepository.save(article2);
		articleRepository.save(article3);
		articleRepository.save(article4);
		articleRepository.save(article5);
		articleRepository.save(article6);
		articleRepository.save(article7);
		articleRepository.save(article8);
		articleRepository.save(article9);

		commentRepository.save(comment1);
		commentRepository.save(comment2);
		commentRepository.save(comment3);
		commentRepository.save(comment4);
		commentRepository.save(comment5);
		commentRepository.save(comment6);
		commentRepository.save(comment7);
		commentRepository.save(comment8);

	}

}
