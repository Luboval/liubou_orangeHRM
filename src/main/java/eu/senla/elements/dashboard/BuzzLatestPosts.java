package eu.senla.elements.dashboard;

public record BuzzLatestPosts(
        String userName,
        String time,
        String text
) {
    /**
     * Канонический конструктор с автоматической нормализацией текста.
     */
    public BuzzLatestPosts {
        // Нормализуем текст сразу при создании любого объекта
        text = normalizeText(text);
    }

    /**
     * Статический фабричный метод без нормализации (если нужен оригинал).
     */
    public static BuzzLatestPosts of(String userName, String time, String text) {
        return new BuzzLatestPosts(userName, time, text);
    }

    /**
     * Статический фабричный метод с нормализацией (по умолчанию).
     */
    public static BuzzLatestPosts ofNormalized(String userName, String time, String text) {
        return new BuzzLatestPosts(userName, time, text); // нормализация в конструкторе
    }

    private static String normalizeText(String text) {
        if (text == null) {
            return null;
        }
        return text
                .replaceAll("\\r\\n?", "\n")
                .replaceAll("\n{3,}", "\n\n")
                .replaceAll("[ \\t]+", " ")
                .replaceAll(" ?\n ?", "\n")
                .trim();
    }
}
