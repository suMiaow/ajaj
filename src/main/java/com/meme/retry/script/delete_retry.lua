local queue_key = KEYS[1];
local detail_key = KEYS[2];
local retry_id = ARGV[1];

redis.call("ZREM", queue_key, retry_id);
redis.call("DEL", detail_key);
