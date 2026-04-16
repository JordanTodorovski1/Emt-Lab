create or replace procedure accommodation_preview_view()
language sql
AS $$
    refresh materialized view concurrently accommodation_preview_view;
$$