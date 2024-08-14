<?php

namespace demonck\okhttp;

use php\concurrent\Promise;
use php\demonck\okhttp\OkHttpClientWrapper;
use php\demonck\okhttp\response\OkHttpResponse;
use php\demonck\okhttp\OkHttpException;
use php\lang\Thread;

class OkHttpClient
{
   public const JSON = "application/json; charset=utf-8";
   public const URLENCODE = "application/x-www-form-urlencoded; charset=utf-8";
   public const FORMDATA = "multipart/form-data; charset=utf-8";
   public const TEXT = "text/plain; charset=utf-8";

   private OkHttpClientWrapper $okHttpClientWrapper;


    private array $properties = [
        'url' => '',
        'userAgent' => 'JPHP-OkHttp/1.0',
        'requestType' => '',
        'connectTimeoutMs' => 3000,
        'readTimeoutMs' => 3000,
        'writeTimeoutMs' => 3000,
        'followRedirects' => true,
        'retryOnConnectionFailure' => false,
        'callTimeoutMs' => 3000,
        'followSslRedirects' => true,
        'headers' => []
    ];

    public function __set($name, $value)
    {
        if (array_key_exists($name, $this->properties)) {
            $this->properties[$name] = $value;
            $this->okHttpClientWrapper->{$name} = $value;
        } else {
            throw new \Exception("Свойство '$name' не найдено");
        }
    }


    public function __get($name)
    {
        if (array_key_exists($name, $this->properties)) {
            return $this->properties[$name];
        } else {
            throw new \Exception("Свойство '$name' не найдено");
        }
    }

   public function __construct()
   {
        $this->okHttpClientWrapper = new OkHttpClientWrapper();
   }

   public function get(string $path) : OkHttpResponse
   {
       return $this->okHttpClientWrapper->execute("GET", $path, json_encode([]));
   }

   public function post(string $path, array $data = null) : OkHttpResponse
   {
       if($data == null){
           $data = json_encode([]);
       }else{
           $data = json_encode($data);
       }

       return $this->okHttpClientWrapper->execute("POST", $path, $data);
   }

    public function put(string $path, array $data = null) : OkHttpResponse
    {
         if($data == null){
              $data = json_encode([]);
         }else{
              $data = json_encode($data);
         }

         return $this->okHttpClientWrapper->execute("PUT", $path, $data);
    }

    public function delete(string $path, array $data = null) : OkHttpResponse
    {
         if($data == null){
              $data = json_encode([]);
         }else{
              $data = json_encode($data);
         }

         return $this->okHttpClientWrapper->execute("DELETE", $path, $data);
    }

    public function options(string $path, array $data = null) : OkHttpResponse
    {
         if($data == null){
              $data = json_encode([]);
         }else{
              $data = json_encode($data);
         }

         return $this->okHttpClientWrapper->execute("OPTIONS", $path, $data);
    }


    public function head(string $path, array $data = null) : OkHttpResponse
    {
         if($data == null){
              $data = json_encode([]);
         }else{
              $data = json_encode($data);
         }

         return $this->okHttpClientWrapper->execute("HEAD", $path,  $data);
    }

    public function patch(string $path, array $data = null) : OkHttpResponse
    {
         if($data == null){
              $data = json_encode([]);
         }else{
              $data = json_encode($data);
         }

         return $this->okHttpClientWrapper->execute("PATCH",$path,  $data);
    }

    public function trace(string $path, array $data = null) : OkHttpResponse
    {
         if($data == null){
              $data = json_encode([]);
         }else{
              $data = json_encode($data);
         }

         return $this->okHttpClientWrapper->execute("TRACE", $path, $data);
    }

    private function connect(string $path, array $data = null) : OkHttpResponse //to-do release
    {
         if($data == null){
              $data = json_encode([]);
         }else{
              $data = json_encode($data);
         }

         return $this->okHttpClientWrapper->execute("CONNECT", $path, $data);
    }

    public function execute(string $method, string $path, array $data = null) : OkHttpResponse
    {
         if($data == null){
              $data = json_encode([]);
         }else{
              $data = json_encode($data);
         }
         return $this->okHttpClientWrapper->execute($method, $path, $data);
    }

    public function asyncExecute(string $method, string $path, array $data = null) : Promise
    {
        $promise = new Promise(function ($resolve, $reject) use ($method, $path, $data) {

            (new Thread(function () use ($resolve, $reject, $method, $path, $data) {

                try{

                    $data = $this->execute($method, $path, $data);

                    $resolve($data);
                }catch(OkHttpException $e){
                    $reject($e);
                }

            }))->start();

        });

        return $promise;
    }

    public function addHeader(string $key, string $value)
    {
        $this->okHttpClientWrapper->addHeader($key, $value);
    }

    public function removeHeader(string $key)
    {
        $this->okHttpClientWrapper->removeHeader($key);
    }

    public function hasHeader(string $key) : bool
    {
       return $this->okHttpClientWrapper->hasHeader($key);
    }

    public function setProxy(string $host, int $port)
    {
      $this->okHttpClientWrapper->setProxy($host, $port);
    }

    public function setProxyWithAuth(string $host, int $port, string $username, string $password)
    {
       $this->okHttpClientWrapper->setProxyWithAuth($host, $port, $username, $password);
    }


}