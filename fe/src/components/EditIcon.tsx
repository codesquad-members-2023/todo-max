import { styled } from 'styled-components';

interface Props {
  width?: string;
  height?: string;
  fill?: string;
}

export default function EditIcon({
  width = '24px',
  height = width,
  fill = '#6E7191',
}: Props) {
  return (
    <StyledEditIcon>
      <svg
        width={width}
        height={height}
        viewBox="0 0 24 24"
        fill="none"
        xmlns="http://www.w3.org/2000/svg"
      >
        <path
          d="M5 16.0837V19H7.91627L16.5173 10.3989L13.6011 7.48268L5 16.0837ZM18.7726 8.1437C18.8447 8.07175 18.9019 7.98629 18.9409 7.89222C18.9799 7.79814 19 7.69729 19 7.59544C19 7.49359 18.9799 7.39274 18.9409 7.29866C18.9019 7.20458 18.8447 7.11912 18.7726 7.04718L16.9528 5.22743C16.8809 5.15533 16.7954 5.09814 16.7013 5.05911C16.6073 5.02009 16.5064 5 16.4046 5C16.3027 5 16.2019 5.02009 16.1078 5.05911C16.0137 5.09814 15.9282 5.15533 15.8563 5.22743L14.4332 6.65057L17.3494 9.56684L18.7726 8.1437Z"
          fill={fill}
        />
      </svg>
    </StyledEditIcon>
  );
}

const StyledEditIcon = styled.div`
  cursor: pointer;
`;
