import styled from 'styled-components';

export const ActionListEmpty = () => {
  return <ListEmptyLayout>사용자 활동 기록이 없습니다.</ListEmptyLayout>;
};

const ListEmptyLayout = styled.li`
  font: ${({ theme: { fonts } }) => fonts.displayM14};
  color: ${({ theme: { colors } }) => colors.textWeak};
  padding: 16px;
  text-align: center;
`;
