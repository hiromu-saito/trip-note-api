INSERT
INTO memories(
    user_id,
    id,
    hotel_name,
    hotel_image,
    impression,
    accommodation_date
)
SELECT
    /* memory.userId */'',
    COALESCE(MAX(id) +1, 1),
    /* memory.hotelName */'',
    /* memory.hotelImage */'',
    /* memory.impression */'',
    /* memory.accommodationDate */''
FROM memories WHERE user_id = /* memory.userId */''