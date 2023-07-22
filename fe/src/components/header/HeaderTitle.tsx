import styled from 'styled-components';

export function HeaderTitle() {
  return <HeaderTitleLayout>TODO List</HeaderTitleLayout>;
}

const HeaderTitleLayout = styled.h1`
  font: ${({ theme: { fonts } }) => fonts.displayB24};
  color: ${({ theme: { colors } }) => colors.textStrong};
`;
