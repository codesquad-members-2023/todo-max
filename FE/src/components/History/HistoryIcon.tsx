export function HistoryIcon({ onClick }: { onClick: () => void }) {
  return (
    <button
      css={{
        border: "none",
        backgroundColor: "transparent",
        cursor: "pointer",
      }}
      onClick={onClick}>
      <img src="/icons/history.svg" alt="history" />
    </button>
  );
}
