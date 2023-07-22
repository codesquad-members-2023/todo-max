export const todolist = {
  statusCode: 200,
  message: [
    {
      processId: 1,
      name: '해야할 일',
      tasks: [
        {
          taskId: 1,
          title: '모던 자바스크립트 예제 실습',
          contents: '1장 예제 내용 실습 후, 커밋까지',
          platform: 'web',
        },
        {
          taskId: 2,
          title: '블로그에 포스팅할 것',
          contents: 'GitHub 공부내용\n모던 자바스크립트 1장 공부 내용',
          platform: 'web',
        },
      ],
    },
    {
      processId: 2,
      name: '하고있는 일',
      tasks: [
        {
          taskId: 3,
          title: 'HTML/CSS 공부하기',
          contents: 'input 태그 실습',
          platform: 'web',
        },
      ],
    },
    {
      processId: 3,
      name: '완료한 일',
      tasks: [],
    },
  ],
};
