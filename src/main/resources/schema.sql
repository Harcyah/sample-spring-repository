create table post
(
    id      integer      not null auto_increment,
    author  varchar(32)  not null,
    title   varchar(128) not null,
    content text         not null,
    primary key (id)
);

create sequence post_id_sequence start with 0 increment by 50;

create table comment
(
    id      integer     not null auto_increment,
    post_id integer     not null,
    author  varchar(32) not null,
    content text        not null,
    primary key (id),
    constraint fk_comment_to_post foreign key (post_id) references post (id)
);

create sequence comment_id_sequence start with 0 increment by 50;
