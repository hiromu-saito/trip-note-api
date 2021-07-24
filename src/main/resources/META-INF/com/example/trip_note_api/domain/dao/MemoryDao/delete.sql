UPDATE memories
SET
    delete_flag = 1
WHERE
    user_id = /* memory.userId */'' AND
    id      = /* memory.id */'';