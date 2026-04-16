create table activities (
                            id bigserial primary key,
                            name varchar(255) not null,
                            created_at timestamp not null,
                            activity_type varchar(255) not null
);