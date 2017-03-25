// default package
// Generated Mar 25, 2017 6:47:10 PM by Hibernate Tools 5.1.0.Alpha1

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * AuthorArticleMapping generated by hbm2java
 */
@Entity
@Table(name = "author_article_mapping", catalog = "dblp")
public class AuthorArticleMapping implements Serializable {

	private AuthorArticleMappingId id;

	public AuthorArticleMapping() {
	}

	public AuthorArticleMapping(AuthorArticleMappingId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "name", column = @Column(name = "name", length = 100) ),
			@AttributeOverride(name = "articleKey", column = @Column(name = "article_key", length = 200) ),
			@AttributeOverride(name = "authorId", column = @Column(name = "Author_Id", nullable = false) ) })
	public AuthorArticleMappingId getId() {
		return this.id;
	}

	public void setId(AuthorArticleMappingId id) {
		this.id = id;
	}

}
