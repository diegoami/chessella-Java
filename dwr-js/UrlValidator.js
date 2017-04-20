function UrlValidator() { }
UrlValidator._path = 'dwr';

UrlValidator.isValid = function(p0, callback) {
    DWREngine._execute(UrlValidator._path, 'UrlValidator', 'isValid', p0, callback);
}