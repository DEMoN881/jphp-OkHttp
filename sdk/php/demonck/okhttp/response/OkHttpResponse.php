<?php
namespace php\demonck\okhttp\response;

class OkHttpResponse{

    public string $body;
    public int $code;
    public string $statusMessage;
    public array $headers;
    public int $contentLength;

}