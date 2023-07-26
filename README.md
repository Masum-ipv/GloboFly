####  Android application is built based on the MVVM architectural pattern

- `Model-View-ViewModel (MVVM)` software design pattern has been followed.
- `Retrofit` is type-safe REST client for Android and Java.
- `Data Binding` library which connects Views with Data..
- `Parcelable` is a serialization mechanism provided by Android.
- `DiffUtils` is used to track changes made to the RecyclerView Adapter.

_As webservice this `Spring Boot` [Application] has been used._

## Installation
Clone the Repository from Github
```sh
git clone git@github.com:Masum-ipv/GloboFly.git
```


# APIs
```sh
    @GET("destination")
    Call<List<Destination>> getDestinations();

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
![alt text](https://github.com/Masum-ipv/GloboFly/blob/master/screenshot/globofly.PNG?raw=true)

[Application]: <https://github.com/Masum-ipv/restapi>
[Tutorial]: https://www.youtube.com/playlist?list=PLlxmoA0rQ-LzEmWs4T99j2w6VnaQVGEtR