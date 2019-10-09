@CommentsFeature
Feature: Comments

  @CommentsToNews
  Scenario: Server does forward news to get its comments
    Given news as a request attribute
    When server calls GET /comments
    Then the status code is OK
    And the response contains a JSON
    And the json contains news with a comments array

  @AddingComment
  Scenario: User posts a form to add comment
    Given comment with filled in author and content
    When a user makes POST request to /comments/save with newsId as a header
    Then the status code is OK

  @UpdatingComment
  Scenario: User posts a form to update comment
    Given comment with filled in id, creation date, author and content
    When a user makes POST request to /comments/save with newsId as a header
    Then the status code is OK

  @DeletingComment
  Scenario: User calls a web service to delete comment by id
    When a user makes GET request to /comments/delete/4
    Then the status code is OK