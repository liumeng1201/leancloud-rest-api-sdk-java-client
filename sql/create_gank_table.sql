CREATE TABLE gank_posts (
	id int(11) NOT NULL auto_increment PRIMARY KEY,
  postId VARCHAR(56) NOT NULL,
  ns VARCHAR(56),
	postTime VARCHAR(56),
	description TEXT NOT NULL,
	publishedAt VARCHAR(56),
	type VARCHAR(56) NOT NULL,
	url VARCHAR(1024) NOT NULL,
	who VARCHAR(56)
  );