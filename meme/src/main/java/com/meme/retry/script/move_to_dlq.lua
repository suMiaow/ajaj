local queue_key = KEYS[1];
local dlq_key = KEYS[2];
local retry_id = ARGV[1];
local retry_start_time = tonumber(ARGV[2]);

redis.call("ZADD", dlq_key, retry_start_time, retry_id);
redis.call("ZREM", queue_key, retry_id);
