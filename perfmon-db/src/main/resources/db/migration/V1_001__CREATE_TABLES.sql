create table STATISTICS (
   name varchar2(255) not null,
   category varchar2(255) not null,
   dt_year number(4),
   dt_month number(2),
   dt_day number(2),
   dt_hour number(2),
   last_updated date,
   total_calls number(10),
   total_time number(10),
   longest number(10),
   shorttest number(10)
);