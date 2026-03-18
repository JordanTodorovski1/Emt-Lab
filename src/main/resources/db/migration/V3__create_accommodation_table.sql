create table accommodations (
    id bigserial primary key,
    created_at timestamp not null,
    updated_at timestamp not null,
    name varchar(255) not null,
    category varchar(255) not null,
    state varchar(255) not null,
    host_id bigint not null references hosts(id) on delete cascade on update cascade,
    num_rooms int not null
);