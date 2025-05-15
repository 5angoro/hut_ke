-- Created by Ebean DDL
-- To stop Ebean DDL generation, remove this comment (both lines) and start using Evolutions

-- !Ups

-- init script create procs
-- Inital script to create stored procedures etc for mysql platform
DROP PROCEDURE IF EXISTS usp_ebean_drop_foreign_keys;

delimiter $$
--
-- PROCEDURE: usp_ebean_drop_foreign_keys TABLE, COLUMN
-- deletes all constraints and foreign keys referring to TABLE.COLUMN
--
CREATE PROCEDURE usp_ebean_drop_foreign_keys(IN p_table_name VARCHAR(255), IN p_column_name VARCHAR(255))
BEGIN
DECLARE done INT DEFAULT FALSE;
DECLARE c_fk_name CHAR(255);
DECLARE curs CURSOR FOR SELECT CONSTRAINT_NAME from information_schema.KEY_COLUMN_USAGE
WHERE TABLE_SCHEMA = DATABASE() and TABLE_NAME = p_table_name and COLUMN_NAME = p_column_name
AND REFERENCED_TABLE_NAME IS NOT NULL;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

OPEN curs;

read_loop: LOOP
FETCH curs INTO c_fk_name;
IF done THEN
LEAVE read_loop;
END IF;
SET @sql = CONCAT('ALTER TABLE `', p_table_name, '` DROP FOREIGN KEY ', c_fk_name);
PREPARE stmt FROM @sql;
EXECUTE stmt;
END LOOP;

CLOSE curs;
END
$$

DROP PROCEDURE IF EXISTS usp_ebean_drop_column;

delimiter $$
--
-- PROCEDURE: usp_ebean_drop_column TABLE, COLUMN
-- deletes the column and ensures that all indices and constraints are dropped first
--
CREATE PROCEDURE usp_ebean_drop_column(IN p_table_name VARCHAR(255), IN p_column_name VARCHAR(255))
BEGIN
CALL usp_ebean_drop_foreign_keys(p_table_name, p_column_name);
SET @sql = CONCAT('ALTER TABLE `', p_table_name, '` DROP COLUMN `', p_column_name, '`');
PREPARE stmt FROM @sql;
EXECUTE stmt;
END
$$
-- apply changes
create table audit_log (
  id                            bigint auto_increment not null,
  user_id                       bigint,
  action                        varchar(255),
  entity_type                   varchar(255),
  entity_id                     bigint,
  old_values                    varchar(255),
  new_values                    varchar(255),
  ip_address                    varchar(255),
  user_agent                    varchar(255),
  created_at                    datetime,
  constraint pk_audit_log primary key (id)
);

create table documents (
  id                            bigint auto_increment not null,
  uploader_id                   bigint,
  related_entity_type           varchar(255),
  related_entity_id             bigint,
  document_type                 varchar(255),
  file_url                      varchar(255),
  file_name                     varchar(255),
  file_size                     bigint,
  file_type                     varchar(255),
  uploaded_at                   datetime,
  constraint pk_documents primary key (id)
);

create table leases (
  id                            bigint auto_increment not null,
  unit_id                       bigint,
  tenant_id                     bigint,
  manager_id                    bigint,
  lease_number                  varchar(255),
  start_date                    date,
  end_date                      date,
  monthly_rent                  double not null,
  deposit_amount                double not null,
  rent_due_day                  integer not null,
  payment_grace_period          integer not null,
  late_fee_percentage           double not null,
  terms                         varchar(255),
  status                        varchar(255),
  constraint pk_leases primary key (id)
);

create table maintenance_assignments (
  id                            bigint auto_increment not null,
  request_id                    bigint,
  assigned_to                   bigint,
  assigned_to_name              varchar(255),
  assigned_by_id                bigint,
  assigned_at                   datetime,
  estimated_completion_date     datetime,
  actual_completion_date        datetime,
  cost_estimate                 double,
  actual_cost                   double,
  notes                         varchar(255),
  constraint pk_maintenance_assignments primary key (id)
);

create table maintenance_requests (
  id                            bigint auto_increment not null,
  unit_id                       bigint,
  tenant_id                     bigint,
  title                         varchar(255),
  description                   varchar(255),
  urgency                       varchar(255),
  status                        varchar(255),
  created_at                    datetime,
  updated_at                    datetime,
  completed_at                  datetime,
  constraint pk_maintenance_requests primary key (id)
);

create table messagas (
  id                            bigint auto_increment not null,
  sender_id                     bigint,
  receiver_id                   bigint,
  subject                       varchar(255),
  body                          varchar(255),
  is_read                       tinyint(1) default 0 not null,
  sent_at                       datetime,
  read_at                       datetime,
  constraint pk_messagas primary key (id)
);

create table notifications (
  id                            bigint auto_increment not null,
  user_id                       bigint,
  title                         varchar(255),
  message                       varchar(255),
  notification_type             varchar(255),
  related_entity_type           varchar(255),
  related_entity_id             bigint,
  is_read                       tinyint(1) default 0 not null,
  created_at                    datetime,
  read_at                       datetime,
  constraint pk_notifications primary key (id)
);

create table payment (
  id                            bigint auto_increment not null,
  lease_id                      bigint,
  amount                        double not null,
  payment_date                  datetime,
  payment_method                varchar(255),
  transaction_reference         varchar(255),
  payment_period_month          integer not null,
  payment_period_year           integer not null,
  is_late                       tinyint(1) default 0 not null,
  late_fee_amount               double not null,
  receipt_generated             tinyint(1) default 0 not null,
  receipt_number                varchar(255),
  receipt_generated_at          datetime,
  notes                         varchar(255),
  constraint pk_payment primary key (id)
);

create table payment_reminders (
  id                            bigint auto_increment not null,
  lease_id                      bigint,
  sent_by_id                    bigint,
  reminder_date                 date,
  reminder_type                 varchar(255),
  message                       varchar(255),
  sent_at                       datetime,
  constraint pk_payment_reminders primary key (id)
);

create table properties (
  id                            varchar(255) not null,
  manager_id                    bigint,
  type_id                       bigint,
  property_name                 varchar(255),
  description                   varchar(255),
  address                       varchar(255),
  city                          varchar(255),
  county                        varchar(255),
  postal_code                   varchar(255),
  latitude                      double,
  longitude                     double,
  total_units                   integer not null,
  available_units               integer not null,
  amenities                     varchar(255),
  images                        varchar(255),
  is_active                     tinyint(1) default 0 not null,
  constraint pk_properties primary key (id)
);

create table users (
  id                            bigint auto_increment not null,
  email                         varchar(255) not null,
  phone_number                  varchar(255) not null,
  password_hash                 varchar(255) not null,
  first_name                    varchar(255) not null,
  last_name                     varchar(255) not null,
  profile_image_url             varchar(255),
  is_active                     tinyint(1) default 0 not null,
  constraint uq_users_email unique (email),
  constraint uq_users_phone_number unique (phone_number),
  constraint pk_users primary key (id)
);

create table property_types (
  id                            bigint auto_increment not null,
  type_name                     varchar(255) not null,
  description                   varchar(255),
  constraint uq_property_types_type_name unique (type_name),
  constraint pk_property_types primary key (id)
);

create table receipts (
  id                            bigint auto_increment not null,
  payment_id                    bigint,
  amount_id                     bigint,
  receipt_number                varchar(255) not null,
  issued_at                     datetime,
  issued_by_id                  bigint,
  download_url                  varchar(255),
  constraint uq_receipts_payment_id unique (payment_id),
  constraint uq_receipts_receipt_number unique (receipt_number),
  constraint pk_receipts primary key (id)
);

create table system_setting (
  id                            bigint auto_increment not null,
  setting_key                   varchar(255) not null,
  setting_value                 varchar(255) not null,
  description                   varchar(255),
  updated_at                    datetime,
  updated_by_id                 bigint,
  constraint uq_system_setting_setting_key unique (setting_key),
  constraint pk_system_setting primary key (id)
);

create table tenants (
  id                            bigint auto_increment not null,
  email                         varchar(255) not null,
  phone_number                  varchar(255) not null,
  password_hash                 varchar(255) not null,
  first_name                    varchar(255) not null,
  last_name                     varchar(255) not null,
  profile_image_url             varchar(255),
  is_active                     tinyint(1) default 0 not null,
  id_number                     varchar(255),
  id_type                       varchar(255),
  id_document_url               varchar(255),
  emergency_contact_name        varchar(255),
  emergency_contact_phone       varchar(255),
  employment_status             varchar(255),
  employer_name                 varchar(255),
  employer_contact              varchar(255),
  monthly_income                double not null,
  constraint uq_tenants_email unique (email),
  constraint uq_tenants_phone_number unique (phone_number),
  constraint pk_tenants primary key (id)
);

create table units (
  id                            bigint auto_increment not null,
  property_id                   varchar(255),
  unit_number                   varchar(255),
  unit_name                     varchar(255),
  bedrooms                      integer not null,
  bathrooms                     double not null,
  size_sqft                     integer,
  monthly_rent                  double not null,
  deposit_amount                double not null,
  features                      varchar(255),
  status                        varchar(255),
  images                        varchar(255),
  constraint pk_units primary key (id)
);

-- foreign keys and indices
create index ix_audit_log_user_id on audit_log (user_id);
alter table audit_log add constraint fk_audit_log_user_id foreign key (user_id) references users (id) on delete restrict on update restrict;

create index ix_documents_uploader_id on documents (uploader_id);
alter table documents add constraint fk_documents_uploader_id foreign key (uploader_id) references users (id) on delete restrict on update restrict;

create index ix_leases_unit_id on leases (unit_id);
alter table leases add constraint fk_leases_unit_id foreign key (unit_id) references units (id) on delete restrict on update restrict;

create index ix_leases_tenant_id on leases (tenant_id);
alter table leases add constraint fk_leases_tenant_id foreign key (tenant_id) references tenants (id) on delete restrict on update restrict;

create index ix_leases_manager_id on leases (manager_id);
alter table leases add constraint fk_leases_manager_id foreign key (manager_id) references users (id) on delete restrict on update restrict;

create index ix_maintenance_assignments_request_id on maintenance_assignments (request_id);
alter table maintenance_assignments add constraint fk_maintenance_assignments_request_id foreign key (request_id) references maintenance_requests (id) on delete restrict on update restrict;

create index ix_maintenance_assignments_assigned_by_id on maintenance_assignments (assigned_by_id);
alter table maintenance_assignments add constraint fk_maintenance_assignments_assigned_by_id foreign key (assigned_by_id) references users (id) on delete restrict on update restrict;

create index ix_maintenance_requests_unit_id on maintenance_requests (unit_id);
alter table maintenance_requests add constraint fk_maintenance_requests_unit_id foreign key (unit_id) references units (id) on delete restrict on update restrict;

create index ix_maintenance_requests_tenant_id on maintenance_requests (tenant_id);
alter table maintenance_requests add constraint fk_maintenance_requests_tenant_id foreign key (tenant_id) references tenants (id) on delete restrict on update restrict;

create index ix_messagas_sender_id on messagas (sender_id);
alter table messagas add constraint fk_messagas_sender_id foreign key (sender_id) references users (id) on delete restrict on update restrict;

create index ix_messagas_receiver_id on messagas (receiver_id);
alter table messagas add constraint fk_messagas_receiver_id foreign key (receiver_id) references users (id) on delete restrict on update restrict;

create index ix_notifications_user_id on notifications (user_id);
alter table notifications add constraint fk_notifications_user_id foreign key (user_id) references users (id) on delete restrict on update restrict;

create index ix_payment_lease_id on payment (lease_id);
alter table payment add constraint fk_payment_lease_id foreign key (lease_id) references leases (id) on delete restrict on update restrict;

create index ix_payment_reminders_lease_id on payment_reminders (lease_id);
alter table payment_reminders add constraint fk_payment_reminders_lease_id foreign key (lease_id) references leases (id) on delete restrict on update restrict;

create index ix_payment_reminders_sent_by_id on payment_reminders (sent_by_id);
alter table payment_reminders add constraint fk_payment_reminders_sent_by_id foreign key (sent_by_id) references users (id) on delete restrict on update restrict;

create index ix_properties_manager_id on properties (manager_id);
alter table properties add constraint fk_properties_manager_id foreign key (manager_id) references users (id) on delete restrict on update restrict;

create index ix_properties_type_id on properties (type_id);
alter table properties add constraint fk_properties_type_id foreign key (type_id) references property_types (id) on delete restrict on update restrict;

alter table receipts add constraint fk_receipts_payment_id foreign key (payment_id) references payment (id) on delete restrict on update restrict;

create index ix_receipts_amount_id on receipts (amount_id);
alter table receipts add constraint fk_receipts_amount_id foreign key (amount_id) references payment (id) on delete restrict on update restrict;

create index ix_receipts_issued_by_id on receipts (issued_by_id);
alter table receipts add constraint fk_receipts_issued_by_id foreign key (issued_by_id) references users (id) on delete restrict on update restrict;

create index ix_system_setting_updated_by_id on system_setting (updated_by_id);
alter table system_setting add constraint fk_system_setting_updated_by_id foreign key (updated_by_id) references users (id) on delete restrict on update restrict;

create index ix_units_property_id on units (property_id);
alter table units add constraint fk_units_property_id foreign key (property_id) references properties (id) on delete restrict on update restrict;


-- !Downs

-- drop all foreign keys
alter table audit_log drop foreign key fk_audit_log_user_id;
drop index ix_audit_log_user_id on audit_log;

alter table documents drop foreign key fk_documents_uploader_id;
drop index ix_documents_uploader_id on documents;

alter table leases drop foreign key fk_leases_unit_id;
drop index ix_leases_unit_id on leases;

alter table leases drop foreign key fk_leases_tenant_id;
drop index ix_leases_tenant_id on leases;

alter table leases drop foreign key fk_leases_manager_id;
drop index ix_leases_manager_id on leases;

alter table maintenance_assignments drop foreign key fk_maintenance_assignments_request_id;
drop index ix_maintenance_assignments_request_id on maintenance_assignments;

alter table maintenance_assignments drop foreign key fk_maintenance_assignments_assigned_by_id;
drop index ix_maintenance_assignments_assigned_by_id on maintenance_assignments;

alter table maintenance_requests drop foreign key fk_maintenance_requests_unit_id;
drop index ix_maintenance_requests_unit_id on maintenance_requests;

alter table maintenance_requests drop foreign key fk_maintenance_requests_tenant_id;
drop index ix_maintenance_requests_tenant_id on maintenance_requests;

alter table messagas drop foreign key fk_messagas_sender_id;
drop index ix_messagas_sender_id on messagas;

alter table messagas drop foreign key fk_messagas_receiver_id;
drop index ix_messagas_receiver_id on messagas;

alter table notifications drop foreign key fk_notifications_user_id;
drop index ix_notifications_user_id on notifications;

alter table payment drop foreign key fk_payment_lease_id;
drop index ix_payment_lease_id on payment;

alter table payment_reminders drop foreign key fk_payment_reminders_lease_id;
drop index ix_payment_reminders_lease_id on payment_reminders;

alter table payment_reminders drop foreign key fk_payment_reminders_sent_by_id;
drop index ix_payment_reminders_sent_by_id on payment_reminders;

alter table properties drop foreign key fk_properties_manager_id;
drop index ix_properties_manager_id on properties;

alter table properties drop foreign key fk_properties_type_id;
drop index ix_properties_type_id on properties;

alter table receipts drop foreign key fk_receipts_payment_id;

alter table receipts drop foreign key fk_receipts_amount_id;
drop index ix_receipts_amount_id on receipts;

alter table receipts drop foreign key fk_receipts_issued_by_id;
drop index ix_receipts_issued_by_id on receipts;

alter table system_setting drop foreign key fk_system_setting_updated_by_id;
drop index ix_system_setting_updated_by_id on system_setting;

alter table units drop foreign key fk_units_property_id;
drop index ix_units_property_id on units;

-- drop all
drop table if exists audit_log;

drop table if exists documents;

drop table if exists leases;

drop table if exists maintenance_assignments;

drop table if exists maintenance_requests;

drop table if exists messagas;

drop table if exists notifications;

drop table if exists payment;

drop table if exists payment_reminders;

drop table if exists properties;

drop table if exists users;

drop table if exists property_types;

drop table if exists receipts;

drop table if exists system_setting;

drop table if exists tenants;

drop table if exists units;

