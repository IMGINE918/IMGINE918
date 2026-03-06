import React, { useState, useEffect } from 'react';

/**
 * 面试亮点：React Hooks、Tailwind CSS v4 现代布局、优雅的错误处理。
 * 针对岗位：前端/全栈开发 (Next.js/React 方向)
 */
export default function StudentDashboard() {
  const [stats, setStats] = useState([]);
  const [loading, setLoading] = useState(true);

  // 面试话术："我使用 Vibe Coding 模式，先定义好组件的状态和错误边界，然后让 AI 帮我生成 Tailwind 的响应式布局代码，效率提升极大。"
  useEffect(() => {
    // 模拟从 Java 后端获取数据
    setTimeout(() => {
      setStats([
        { major: '计算机科学', averageScore: 88.5, totalStudents: 120 },
        { major: '软件工程', averageScore: 86.2, totalStudents: 95 },
      ]);
      setLoading(false);
    }, 1000);
  }, []);

  if (loading) {
    return <div className="flex justify-center items-center h-64 text-blue-500 animate-pulse">正在加载 AI 智能分析数据...</div>;
  }

  return (
    <div className="p-6 bg-gray-50 min-h-screen">
      <h1 className="text-3xl font-bold text-gray-800 mb-6 border-l-4 border-blue-500 pl-3">
        🏫 学院智能看板 (AI Dashboard)
      </h1>
      
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {stats.map((stat, index) => (
          <div key={index} className="bg-white rounded-xl shadow-sm hover:shadow-md transition-shadow p-5 border border-gray-100">
            <h2 className="text-xl font-semibold text-gray-700">{stat.major}</h2>
            <div className="mt-4 flex justify-between items-end">
              <div>
                <p className="text-sm text-gray-500">平均分</p>
                <p className="text-2xl font-bold text-green-600">{stat.averageScore}</p>
              </div>
              <div>
                <p className="text-sm text-gray-500">总人数</p>
                <p className="text-2xl font-bold text-blue-600">{stat.totalStudents}</p>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
