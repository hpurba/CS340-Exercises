public class Entry {
  public String follower_handle;
  public String followee_handle;
  public String follower_name;
  public String followee_name;

  public Entry(String _follower_handle, String _followee_handle, String _follower_name, String _followee_name) {
    follower_handle = _follower_handle;
    followee_handle = _followee_handle;
    follower_name = _follower_name;
    followee_name = _followee_name;
  }

  @Override
  public String toString() {
    return String.format("TestCase[%s, %s, %s, %s]", follower_handle, followee_handle, follower_name, followee_name);
  }
}
