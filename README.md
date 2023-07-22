`Retrofit` is a REST Client for Java and Android. It makes it relatively easy to retrieve and upload JSON (or other structured data) via a REST based webservice. 

`Model-View-ViewModel (MVVM)` software design pattern have been used.
### _This project is based on this [Tutorial]_
### _As webservice this Speing Boot [Application] can be used_

## Installation
Clone the Repository from Github
```sh
git clone git@github.com:Masum-ipv/GloboFly.git
```


# APIs
```sh
    @GET("destination")
    Call<List<Destination>> doGetListResources();

    @GET("messages")
    Call<String> getMessage();

    @GET("destination/{id}")
    Call<Destination> getDestination(@Path("id") int id);

    @POST("destination")
    Call<Destination> addDestination(@Body Destination destination);

    @PUT("destination/{id}")
    Call<Destination> updateDestination(@Path("id") int id, @Body Destination destination);

    @DELETE("destination/{id}")
    Call<Destination> deleteDestination(@Path("id") int id);
```

[Application]: <https://github.com/Masum-ipv/restapi>
[Tutorial]: https://www.youtube.com/playlist?list=PLlxmoA0rQ-LzEmWs4T99j2w6VnaQVGEtR
