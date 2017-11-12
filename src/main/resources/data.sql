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
  ('Marge', 'Simpson', '1953-08-09', 'Female', 1, 2, null),
  ('Marie', 'Nelson', '1946-07-12', 'Female', 1, 1, null),
  ('Mary', 'Cassidy', '1947-03-06', 'Female', 1, 2, null),
  ('Bain', 'Simpson', '1945-07-29', 'Male', 1, 2, null);

UPDATE clients SET partner_id = 5 WHERE id = 2;
UPDATE clients SET partner_id = 2 WHERE id = 5;
UPDATE clients SET partner_id = 8 WHERE id = 7;
UPDATE clients SET partner_id = 7 WHERE id = 8;

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

INSERT INTO income_stream_types (id, name, asset) VALUES
  (1, 'Salary', false),
  (2, 'Account based pension (pre 1 Jan 2015)', true),
  (3, 'Term allocated pension', true),
  (4, 'Annuity', true),
  (5, 'Defined benefit pension', true),
  (6, 'Rent', false),
  (7, 'Other', false);

INSERT INTO asset_types (id, name, deemed) VALUES
  (1, 'Home contents', false),
  (2, 'Vehicle', false),
  (3, 'Boat', false),
  (4, 'Caravan', false),
  (5, 'Principal home sale proceeds', true),
  (6, 'Funeral bond', false),
  (7, 'Prepaid funeral', false),
  (8, 'Shares', true),
  (9, 'Managed fund', true),
  (10, 'Bank account', true),
  (11, 'Term Deposit', true),
  (12, 'Account Based Pension (pre 1 Jan 2015)', false),
  (13, 'Account Based Pension (post 1 Jan 2015)', true),
  (14, 'Term Allocated Pension (0% exempt)', false),
  (15, 'Term Allocated Pension (50% exempt)', false),
  (16, 'Term Allocated Pension (100% exempt)', false),
  (17, 'Annuity', false),
  (18, 'Superannuation', true),
  (19, 'Insurance', false),
  (20, 'Other (Not deemed)', false),
  (21, 'Other (Deemed)', true);

INSERT INTO age_rules (date_of_birth_start, date_of_birth_end, eligible_age) VALUES
  (null, '1952-06-30', 65),
  ('1952-07-01', '1953-12-31', 65.5),
  ('1954-01-01', '1955-06-30', 66),
  ('1955-07-01', '1956-12-31', 66.5),
  ('1957-07-01', null, 67);

INSERT INTO calculations (client_id, date, payment, applicable_test, comment) VALUES
  (1, '2017-04-01', 546.94, 'Assets Test', 'Initial calculation'),
  (1, '2017-06-14', 558.11, 'Assets Test', 'Purchased funeral bond'),
  (1, '2017-09-25', 365.39, 'Income Test', 'Started part time job'),
  (6, '2017-11-10', 599.13, 'Assets Test', 'Initial calculation'),
  (7, '2017-11-10', 290.41, 'Assets Test', 'Initial calculation');

INSERT INTO assets (calculation_id, asset_type_id, description, value) VALUES
  (1, 1, 'Centrelink value', 5000),
  (1, 2, '2001 Honda CRV', 4000),
  (1, 8, 'CBA shares', 250000),
  (1, 9, 'Magellan Global Fund', 70200),
  (1, 12, 'Russell ABP', 100000),
  (1, 13, 'Russell ABP2', 80000),
  (2, 1, 'Centrelink value', 5000),
  (2, 2, '2001 Honda CRV', 4000),
  (2, 8, 'CBA shares', 250000),
  (2, 9, 'Magellan Global Fund', 70200),
  (2, 12, 'Russell ABP', 100000),
  (2, 13, 'Russell ABP2', 80000),
  (3, 1, 'Centrelink value', 5000),
  (3, 2, '2001 Honda CRV', 4000),
  (3, 8, 'CBA shares', 250000),
  (3, 9, 'Magellan Global Fund', 70200),
  (3, 12, 'Russell ABP', 100000),
  (3, 13, 'Russell ABP2', 80000),
  (4, 1, 'Centrelink value', 5000),
  (4, 2, 'Motor vehicles', 15000),
  (5, 1, 'Centrelink value', 8000),
  (5, 2, '2007 Toyota Camry (Mary)', 6500),
  (5, 2, '2004 Mitsubishi Pajero (Bain)', 3000),
  (5, 20, 'Civil Celebrant Assets', 2831),
  (5, 10, 'Westpac Savings Account - 68-5969', 41410),
  (5, 10, 'Westpac Savings Account - 62-2096', 3000),
  (5, 10, 'BankWest - 0034050', 2614),
  (5, 10, 'NAB Cheque Account - 505935768', 1565),
  (5, 10, 'Westpac Savings Account - 35-6762', 4513),
  (5, 9, 'CFS Managed Investments', 94853),
  (5, 18, 'Synergy', 12128),
  (5, 12, 'ABP (Mary)', 147023),
  (5, 12, 'CFGS ABP (Bain)', 161333),
  (5, 17, 'Challenger Annuity (Mary)', 147591);

INSERT INTO income_streams (calculation_id, income_stream_type_id, description, deductible_amount, annual_payment, current_balance, assessed_income) VALUES
  (1, 2, 'Russell ABP', 3750, 8000, 100000, 4250),
  (1, 4, 'Challenger Lifetime Annuity', null, null, null, 2800),
  (2, 2, 'Russell ABP', 3750, 8000, 100000, 4250),
  (2, 4, 'Challenger Lifetime Annuity', null, null, null, 2800),
  (3, 2, 'Russell ABP', 3750, 8000, 100000, 4250),
  (3, 4, 'Challenger Lifetime Annuity', null, null, 50000, 2800),
  (5, 2, 'ABP (Mary)', 6745, 8647, null, 1902),
  (5, 2, 'CFGS ABP (Bain)', 9723, 17358, null, 7635),
  (5, 4, 'Challenger Annuity (Mary)', 6938, 7878, null, 940),
  (5, 7, 'Austrian Pension (Bain)', null, null, null, 115.31),
  (5, 1, 'Civil Celebrant (Mary)', null, null, null, 3954),
  (5, 7, 'CHWASTA Muir''s (Bain)', null, null, null, 728);

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
  (1, 2, 292, 0.25),
  (2, 1, 168, 0.5),
  (2, 2, 300, 0.25);

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

INSERT INTO users (username, password, name, email, enabled) VALUES
  ('rodmcc', '123', 'Rod McCutcheon', 'rodmccutcheon@gmail.com', true),
  ('johnmcc', '123', 'John McCutcheon', 'john@balwynfs.com.au', true);
