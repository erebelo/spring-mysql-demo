start transaction;

-- Address
insert into address (city, country, name, state, zip_code) values ("New York", "US", "Street 9th", "NY", 12345);
set @address_id_1 = last_insert_id();

insert into address (city, country, name, state, zip_code) values ("Boston", "US", "Lewiston St", "MA", 67892);
set @address_id_2 = last_insert_id();

-- Broker Type
insert into broker_type (name) values ("Firm") ;
set @broker_type_id_1 = last_insert_id();

insert into broker_type (name) values ("Dealer");
set @broker_type_id_2 = last_insert_id();

-- Advisor
insert into advisor (name, status) values ("Mia", "Active");
set @advisor_id_1 = last_insert_id();

insert into advisor (name, status) values ("John", "Active");
set @advisor_id_2 = last_insert_id();

insert into advisor (name, status) values ("Peter", "Active");
set @advisor_id_3 = last_insert_id();

insert into advisor (name, status) values ("Joseph", "Active");
set @advisor_id_4 = last_insert_id();

-- Broker
insert into broker (name, description, address_id, broker_type_id) values ('Broker One', 'Description of Broker One', @address_id_1, @broker_type_id_1);
set @broker_id_1 = last_insert_id();

insert into broker (name, description, address_id, broker_type_id) values ('Broker Two', 'Description of Broker Two',  @address_id_2, @broker_type_id_2);
set @broker_id_2 = last_insert_id();

-- Broker-Advisor Association
insert into broker_advisor (broker_id, advisor_id, status, start_date, end_date)
values (@broker_id_1, @advisor_id_1, "Active", "2023-01-30", "2024-12-31"),
(@broker_id_1, @advisor_id_2, "Pending", "2024-03-31", "2025-10-20");

insert into broker_advisor (broker_id, advisor_id, status, start_date, end_date)
values (@broker_id_2, @advisor_id_3, "Terminated", "2022-09-30", "2024-01-01"),
(@broker_id_2, @advisor_id_4, "Active", "2021-06-10", "2025-12-12");

-- Broker-Broker Association
insert into broker_broker (broker_id, associated_broker_id) values (@broker_id_1, @broker_id_2);

commit;