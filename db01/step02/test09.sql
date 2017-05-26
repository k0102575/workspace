auto_increment 옵션



방법1) 테이블 정의할 떄 붙이기
create table t2 (
  no int primary key auto_increment,
  name varchar(10),
  email varchar(50)
);

방법2) 테이블 정의한 후 auto_increment 붙이기

create table t2 (
  no int,
  name varchar(10),
  email varchar(50)
);

alter table t2
  add constraint primary key (no);

기존의 PK 컬럼을 auto_increment로 만들기
alter table t2
  modify column no int auto_increment;

예제 데이터 입력
insert into t2(name, email) values('홍길동', 'hong@test.com');

insert into t2(name, email) values('홍길동2', 'hong2@test.com');

insert into t2(name, email) values('홍길동3', 'hong3@test.com');

번호에 구멍을 내보자
delete from t2 where no=2;

insert into t2(name, email) values('홍길동3', 'hong3@test.com');
