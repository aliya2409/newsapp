@NewsFeature
Feature: News

  @AllNews
  Scenario: User calls a web service to get all news
    When a user calls GET /news/allnews
    Then the status code is 200
    And response should includes a list of news

  @IndividualNews
  Scenario: User calls a web service to get a news by it's id
    When a user makes GET request to /news/2
    Then response is a forward to /comments
    And the status code is 200

  @AddingNews
  Scenario: User posts a form to add news
    Given news with filled in title, brief and content
    When a user makes POST request to /news/saveNews
    Then the status code is 200

  @UpdatingNews
  Scenario: User posts a form to update news
    Given news with filled in id, creation date, title, brief and content
    When a user makes POST request to /news/saveNews
    Then the status code is 200

  @DeletingNews
  Scenario: User calls a web service to delete news by id
    When a user makes GET request to /news/delete/2
    Then the status code is 200

  @GettingDeletedNews
  Scenario: User tries to get not existing news
    When a user makes GET request to /news/3
    Then the status code is 404
    And the response contains a json
    And the json's 'message' property includes 'Could not find news with id: 3'

  @DeletingListNews
  Scenario: User calls a web service to delete a list of news ids
    Given a list of news ids
    When a user makes POST request to /news/delete
    Then the status code is 200