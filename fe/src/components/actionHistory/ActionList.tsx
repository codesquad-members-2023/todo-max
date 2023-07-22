import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import { ActionListEmpty } from './ActionListEmpty';
import { ActionListItem } from './ActionListItem';
import { Button } from '../buttons/Button';
import { Modal } from '../modal/Modal';
import { useData } from '../../contexts/DataContext';

type History = {
  title: string;
  from: string;
  to: string;
  action: string;
  createdTime: string;
  userName: string;
  imageUrl: string;
};

export const ActionList = () => {
  const [isVisible, setIsVisible] = useState(false);
  const [historyData, setHistoryData] = useState<History[] | null>(null);
  const { todoListData } = useData();

  const fetchInitialData = async () => {
    const response = await fetch('/api/history');
    const data = await response.json();
    setHistoryData(data.message);
  };

  useEffect(() => {
    fetchInitialData();
  }, [todoListData]);


  if (historyData === null) {
    return <div>Loading...</div>;
  }

  const isListEmpty = historyData.length === 0;

  const handleClose = () => {
    setIsVisible((prevVisible) => !prevVisible);
  };

  const handleDelete = async () => {
    const response = await fetch('/api/history', {
      method: 'DELETE',
    });
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    setHistoryData([]);
    setIsVisible((prevVisible) => !prevVisible);
  };

  return (
    <>
      <ActionListLayout>
        {isListEmpty ? (
          <ActionListEmpty />
        ) : (
          <>
            {historyData.map((item: History, index: number) => (
              <React.Fragment key={index}>
                <ActionListItem {...item} />
                {index !== historyData.length - 1 && (
                  <DividingLineLayout></DividingLineLayout>
                )}
              </React.Fragment>
            ))}
            <ButtonLayout>
              <Button
                variant="ghost"
                pattern="text-only"
                text="기록 전체 삭제"
                onClick={handleClose}
              />
            </ButtonLayout>
          </>
        )}
      </ActionListLayout>
      {isVisible && (
        <Modal
          alertText="모든 사용자 활동 기록을 삭제할까요?"
          onClose={handleClose}
          onClick={handleDelete}
        />
      )}
    </>
  );
};

const ActionListLayout = styled.ul`
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 350px;
`;

const ButtonLayout = styled.div`
  display: flex;
  justify-content: flex-end;
  align-items: center;
  padding: 8px 16px;
`;

const DividingLineLayout = styled.div`
  width: 100%;
  height: 1px;
  background-color: ${({ theme: { colors } }) => colors.borderDefault};
`;
