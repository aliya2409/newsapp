CREATE TABLE publication (
  id number(19) NOT NULL,
  content varchar2(255) NOT NULL,
  cr_date date NOT NULL,
  CONSTRAINT pk_publication_id PRIMARY KEY (id)
);
CREATE TABLE news (
  id number(19) NOT NULL,
  title varchar2(100) NOT NULL,
  brief varchar2(100) NOT NULL,
  CONSTRAINT pk_news_id PRIMARY KEY (id),
  CONSTRAINT fk_news_publication_id FOREIGN KEY (id) REFERENCES publication (id) ON DELETE CASCADE
);
CREATE TABLE commentary (
  id number(19) NOT NULL,
  author varchar2(50) NOT NULL,
  news_id number(19) NOT NULL,
  CONSTRAINT pk_comment_id PRIMARY KEY (id),
  CONSTRAINT fk_comment_news_news_id FOREIGN KEY (news_id) REFERENCES news (id) ON DELETE CASCADE,
  CONSTRAINT fk_comment_publication_id FOREIGN KEY (id) REFERENCES publication (id) ON DELETE CASCADE
);
CREATE SEQUENCE publication_seq start with 1 increment by 1;