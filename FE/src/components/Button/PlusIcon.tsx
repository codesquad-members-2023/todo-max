type Props = {
  width: string;
  height: string;
  color: string;
};

export function PlusIcon({ width, height, color }: Props) {
  return (
    <svg
      width={width}
      height={height}
      viewBox="0 0 24 24"
      fill="none"
      xmlns="http://www.w3.org/2000/svg">
      <path
        d="M19 12.998H13V18.998H11V12.998H5V10.998H11V4.99799H13V10.998H19V12.998Z"
        fill={color}
      />
    </svg>
  );
}
