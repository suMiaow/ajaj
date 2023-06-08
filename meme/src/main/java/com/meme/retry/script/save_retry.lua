local queue_key = KEYS[1];
local detail_key = KEYS[2];
local retry_id = ARGV[1];
local next_retry_time = tonumber(ARGV[2]);
local retry_detail = ARGV[3];

redis.call("ZADD", queue_key, next_retry_time, retry_id);
redis.call("SET", detail_key, retry_detail);
