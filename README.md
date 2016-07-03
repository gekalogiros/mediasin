Mediasin
======

Mediasin is a simple crypto command line tool for creating digital signatures and signing/verifying data files.

## Build

`gradle build`

## Usage

* Sign data
```bash
java -jar signer.jar <path_to_file_to_be_signed>
```

* Verify signature
```bash
java -jar verifier.jar <path_to_file_to_be_verified> <file_containing_the_public_key> <file_containing_the_digital_signature>
```

## Testing

For testing purposes there is a pair of keys generated under the verifier module. Please *DO NOT* use this pair for any other case rather than running the tests.

## License
The content of this project itself is licensed under the [Creative Commons Attribution 3.0 license](http://creativecommons.org/licenses/by/3.0/us/deed.en_US), and the underlying source code used to format and display that content is licensed under the [MIT license](http://opensource.org/licenses/mit-license.php).