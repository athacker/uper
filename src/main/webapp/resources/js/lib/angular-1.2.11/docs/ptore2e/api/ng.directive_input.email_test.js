describe("api/ng.directive:input.email", function() {
  beforeEach(function() {
    browser.get("index-nocache.html#!/api/ng.directive:input.email");
  });

  var text = element(by.binding('text'));
  var valid = element(by.binding('myForm.input.$valid'));
  var input = element(by.model('text'));
  
  it('should initialize to model', function() {
    expect(text.getText()).toContain('me@example.com');
    expect(valid.getText()).toContain('true');
  });
  
  it('should be invalid if empty', function() {
    input.clear();
    input.sendKeys('');
    expect(text.getText()).toEqual('text =');
    expect(valid.getText()).toContain('false');
  });
  
  it('should be invalid if not email', function() {
    input.clear();
    input.sendKeys('xxx');
  
    expect(valid.getText()).toContain('false');
  });

});
