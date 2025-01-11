String sql = "INSERT INTO users (name, age) VALUES (?, ?)";
try (Connection conn = dataSource.getConnection();
     PreparedStatement ps = conn.prepareStatement(sql)) {

    for (int i = 0; i < 100; i++) {
        ps.setString(1, "User " + i);
        ps.setInt(2, 20 + i);
        ps.addBatch();
    }
    ps.executeBatch();
}
