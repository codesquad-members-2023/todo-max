import styled from 'styled-components';

type ActionListItemProps = {
  title: string;
  from: string;
  to: string;
  action: string;
  createdTime: string;
  userName: string;
  imageUrl: string;
};


function formatTimeDifference(dateTimeStr: string): string {
  const inputDate = new Date(dateTimeStr);
  const diffInMinutes = Math.floor(
    (new Date().getTime() - inputDate.getTime()) / 60000,
  );

  if (diffInMinutes < 60) {
    return `${diffInMinutes}분전`;
  }

  const diffInHours = Math.floor(diffInMinutes / 60);
  if (diffInHours < 24) {
    return `${diffInHours}시간전`;
  }

  const diffInDays = Math.floor(diffInHours / 24);
  if (diffInDays < 7) {
    return `${diffInDays}일전`;
  }

  const diffInWeeks = Math.floor(diffInDays / 7);
  return `${diffInWeeks}주전`;
}

export const ActionListItem: React.FC<ActionListItemProps> = ({
  title,
  from,
  to,
  action,
  createdTime,
  userName,
  imageUrl,
}) => {
  return (
    <ListItemLayout>
      <img src={imageUrl} alt="UserImage" />
      <div className="itemBody">
        <span className="userName">@{userName}</span>
        <span className="actionBody">
          <span className="bold">{title}</span>
          을(를){' '}
          {from && (
            <>
              <span className="bold">{from}</span>
              <span>에서 </span>
            </>
          )}
          {to && (
            <>
              <span className="bold">{to}</span>
              <span>으로 </span>
            </>
          )}

          <span className="bold">{action}</span>
          하였습니다.
        </span>
        <span className="createdTime">{formatTimeDifference(createdTime)}</span>
      </div>
    </ListItemLayout>
  );
};

const ListItemLayout = styled.li`
  display: flex;
  align-items: flex-start;
  gap: 16px;
  padding: 16px;

  .itemBody {
    display: flex;
    flex-direction: column;
    gap: 8px;
    flex: 1 0 0;
    align-items: flex-start;
  }

  img {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    border: 1px solid ${({ theme: { colors } }) => colors.borderDefault};
  }

  .userName {
    font: ${({ theme: { fonts } }) => fonts.displayM14};
    color: ${({ theme: { colors } }) => colors.textDefault};
  }

  .actionBody {
    font: ${({ theme: { fonts } }) => fonts.displayM14};
    color: ${({ theme: { colors } }) => colors.textDefault};
  }

  .bold {
    font: ${({ theme: { fonts } }) => fonts.displayB14};
    color: ${({ theme: { colors } }) => colors.textBold};
  }

  .createdTime {
    font: ${({ theme: { fonts } }) => fonts.displayM12};
    color: ${({ theme: { colors } }) => colors.textWeak};
  }
`;
