INSERT INTO relationship_status (id, name) VALUES
  (1, 'Single'),
  (2, 'Couple'),
  (3, 'Couple illness separated');

INSERT INTO homeowner_status (id, name) VALUES
  (1, 'Homeowner'),
  (2, 'Non homeowner');

INSERT INTO clients (first_name, last_name, date_of_birth, gender, homeowner_status_id, relationship_status_id, partner_id) VALUES
  ('Max', 'Power', '1940-01-01', 'Male', 2, 1, null),
  ('Homer', 'Simpson', '1953-08-09', 'Male', 1, 2, null),
  ('Seymour', 'Skinner', '1953-08-09', 'Male', 1, 1, null),
  ('Ned', 'Flanders', '1953-08-09', 'Male', 1, 3, null),
  ('Marge', 'Simpson', '1953-08-09', 'Female', 1, 2, null);

UPDATE clients SET partner_id = 5 WHERE id = 2;
UPDATE clients SET partner_id = 2 WHERE id = 5;

INSERT INTO payment_rate_groups (start_date, end_date) VALUES
  ('2017-03-20', '2017-09-19'),
  ('2017-09-20', '2018-03-19');

INSERT INTO payment_rates (payment_rate_group_id, relationship_status_id, maximum_basic_rate, maximum_pension_supplement, clean_energy_supplement) VALUES
  (1, 1, 808.30, 65.90, 14.10),
  (1, 2, 609.30, 49.70, 10.60),
  (1, 3, 808.30, 65.90, 14.10),
  (2, 1, 814.00, 66.30, 14.10),
  (2, 2, 613.60, 50.00, 10.60),
  (2, 3, 814.00, 66.30, 14.10);

INSERT INTO income_stream_types (id, name) VALUES
  (1, 'Salary'),
  (2, 'Account based pension'),
  (3, 'Term allocated pension'),
  (4, 'Annuity'),
  (5, 'Defined benefit pension'),
  (6, 'Rent');

INSERT INTO asset_types (id, name, deemed) VALUES
  (1, 'Home contents', false),
  (2, 'Vehicle', false),
  (3, 'Boat', false),
  (4, 'Caravan', false),
  (5, 'Principal home sale proceeds', false),
  (6, 'Funeral bond', false),
  (7, 'Prepaid funeral', false),
  (8, 'Shares', true),
  (9, 'Managed fund', true),
  (10, 'Bank account', true),
  (11, 'Term Deposit', true);

INSERT INTO age_rules (date_of_birth_start, date_of_birth_end, eligible_age) VALUES
  (null, '1952-06-30', 65),
  ('1952-07-01', '1953-12-31', 65.5),
  ('1954-01-01', '1955-06-30', 66),
  ('1955-07-01', '1956-12-31', 66.5),
  ('1957-07-01', null, 67);

INSERT INTO calculations (client_id, date, payment, applicable_test, comment) VALUES
  (1, '2017-01-01', 546.94, 'Assets Test', 'Initial calculation'),
  (1, '2017-06-14', 558.11, 'Assets Test', 'Purchased funeral bond'),
  (1, '2017-09-25', 365.39, 'Income Test', 'Started part time job');

INSERT INTO assets (calculation_id, asset_type_id, description, value) VALUES
  (3, 1, 'Centrelink value', 5000),
  (3, 2, '2001 Honda CRV', 4000),
  (3, 8, 'CBA shares', 300000),
  (3, 9, 'Magellan Global Fund', 250200);

INSERT INTO income_streams (calculation_id, income_stream_type_id, description, value) VALUES
  (3, 2, 'Russell ABP', 4250),
  (3, 4, 'Challenger Lifetime Annuity', 2800);

INSERT INTO assets_test_threshold_groups (start_date, end_date) VALUES
  ('2016-07-01', '2017-06-30'),
  ('2017-07-01', '2018-06-30');

INSERT INTO assets_test_thresholds (assets_test_threshold_group_id, relationship_status_id, homeowner_status_id, threshold, reduction_rate) VALUES
  (1, 1, 1, 250000, 3),
  (1, 1, 2, 450000, 3),
  (1, 2, 1, 375000, 1.5),
  (1, 2, 2, 575000, 1.5),
  (1, 3, 1, 375000, 1.5),
  (1, 3, 2, 575000, 1.5),
  (2, 1, 1, 253750, 3),
  (2, 1, 2, 456750, 3),
  (2, 2, 1, 380500, 1.5),
  (2, 2, 2, 583500, 1.5),
  (2, 3, 1, 380500, 1.5),
  (2, 3, 2, 583500, 1.5);

INSERT INTO income_test_threshold_groups (start_date, end_date) VALUES
  ('2016-07-01', '2017-06-30'),
  ('2017-07-01', '2018-06-30');

INSERT INTO income_test_thresholds (income_test_threshold_group_id, relationship_status_id, threshold, reduction_rate) VALUES
  (1, 1, 164, 0.5),
  (1, 2, 292, 0.5),
  (2, 1, 168, 0.5),
  (2, 2, 300, 0.5);

INSERT INTO deeming_rate_groups (start_date, end_date) VALUES
  ('2016-07-01', '2017-06-30'),
  ('2017-07-01', '2018-06-30');

INSERT INTO deeming_rates (deeming_rate_group_id, relationship_status_id, threshold_start, threshold_end, deeming_rate) VALUES
  (1, 1, 0, 49200, 1.75),
  (1, 1, 49200, null, 3.25),
  (1, 2, 0, 81600, 1.75),
  (1, 2, 81600, null, 3.25),
  (2, 1, 0, 50200, 1.75),
  (2, 1, 50200, null, 3.25),
  (2, 2, 0, 83400, 1.75),
  (2, 2, 83400, null, 3.25);