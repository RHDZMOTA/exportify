# Exportify [![experimental](http://badges.github.io/stability-badges/dist/experimental.svg)](http://github.com/badges/stability-badges)

Export your playlist from Spotify to JSON. 

**Requirements** 
* Simple Build Tool (sbt) and its dependencies
* Scala 2.12

## Usage

Clone this repo and use `sbt` to run the application.

```bash
sbt "run {action-flag} {service-flag} {email} {password} {parallel-flag}"
```

Options:
```
    {action-flag} The action-flag indicates the operation to do with the account. Substitute with:
        -download   : Returns the content of the account as JSON.
        -upload     : [Not available]
       
    {service-flag} The service-flag indicates which streaming service is the target. Substitute with:
        -spotify    : Uses the spotify music streaming service.
        -gmusic     : [Not available]
        
    {email} Specify the user's email (e.g. your@email.com)
    
    {password} Specify the user's password for the service (e.g. 123456789)
    
    {parallel-flag} The parallel-flag indicates whether to use futures to download each playlist in parralell or not. Substitute with:
        -parallel   : Downloads each playlist in parallel. 
                    : Leave blank for a sequential download. 
```

Example: 

```bash
sbt "run -download -spotify your@email.com 123456789"

sbt "run -download -spotify your@email.com 123456789 -parallel"
```

## Test

Use `sbt` to run tests: `sbt test`

 
## Contributions and authors

Contact the main developer for contributions and inquiries: 

* **Main developer**: Rodrigo Hernández Mota (rohdzmota@gmail.com)

## License
<a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/">
  <img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-sa/4.0/88x31.png" />
</a><br /><span xmlns:dct="http://purl.org/dc/terms/" property="dct:title">Exportify
</span> by <a xmlns:cc="http://creativecommons.org/ns#" href="https://github.com/rhdzmota/exportify" property="cc:attributionName" rel="cc:attributionURL">Rodrigo Hernández Mota</a> 
is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/">
Creative Commons Attribution-ShareAlike 4.0 International License</a>.

## Acknowledgements

Not so far.