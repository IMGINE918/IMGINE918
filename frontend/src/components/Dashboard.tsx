import { useEffect, useState } from "react";

type Student = {
  studentId: string;
  name: string;
  major: string;
  score: number;
};

type MajorStat = {
  major: string;
  averageScore: number;
  totalStudents: number;
};

type HealthPayload = {
  ok?: boolean;
  service?: string;
};

const fallbackStats: MajorStat[] = [
  { major: "Computer Science", averageScore: 88.5, totalStudents: 120 },
  { major: "Software Engineering", averageScore: 86.2, totalStudents: 95 },
  { major: "Data Science", averageScore: 83.4, totalStudents: 72 }
];

const fallbackStudents: Student[] = [
  { studentId: "2026001", name: "Zhang San", major: "Software Engineering", score: 85.5 },
  { studentId: "2026002", name: "Li Si", major: "Computer Science", score: 92.0 },
  { studentId: "2026003", name: "Wang Wu", major: "Software Engineering", score: 58.0 }
];

export default function Dashboard() {
  const [stats, setStats] = useState<MajorStat[]>(fallbackStats);
  const [students, setStudents] = useState<Student[]>(fallbackStudents);
  const [loading, setLoading] = useState(true);
  const [source, setSource] = useState("mock");
  const [backendStatus, setBackendStatus] = useState("disconnected");

  useEffect(() => {
    const loadData = async () => {
      try {
        const [healthResponse, statsResponse, studentsResponse] = await Promise.all([
          fetch("/api/health"),
          fetch("/api/students/statistics/majors"),
          fetch("/api/students")
        ]);

        if (!healthResponse.ok || !statsResponse.ok || !studentsResponse.ok) {
          throw new Error("Backend unavailable");
        }

        const healthData = (await healthResponse.json()) as HealthPayload;
        const statsData = (await statsResponse.json()) as MajorStat[];
        const studentsData = (await studentsResponse.json()) as Student[];

        setStats(statsData);
        setStudents(studentsData);
        setSource("backend");
        setBackendStatus(healthData.ok ? "connected" : "unknown");
      } catch {
        setSource("mock");
        setBackendStatus("disconnected");
      } finally {
        setLoading(false);
      }
    };

    void loadData();
  }, []);

  const atRiskCount = students.filter((student) => student.score < 60).length;

  return (
    <main className="page-shell">
      <section className="hero">
        <div>
          <p className="eyebrow">AI-Native Practice Frontend</p>
          <h1>Student Management Dashboard</h1>
          <p className="hero-copy">
            This frontend shell now prefers live backend data and falls back to mock data when the
            Java API is unavailable.
          </p>
        </div>
        <div className="status-card">
          <span className={`status-dot ${source}`}></span>
          <span>Data source: {source}</span>
        </div>
      </section>

      <section className="summary-grid">
        <SummaryCard label="Majors" value={String(stats.length)} />
        <SummaryCard label="Students" value={String(students.length)} />
        <SummaryCard label="At Risk" value={String(atRiskCount)} />
        <SummaryCard label="Backend" value={backendStatus} />
      </section>

      <section className="content-grid">
        <div className="panel">
          <div className="panel-header">
            <h2>Major Statistics</h2>
            {loading ? <span>Loading...</span> : <span>Ready</span>}
          </div>
          <div className="stats-grid">
            {stats.map((stat) => (
              <article key={stat.major} className="stat-card">
                <h3>{stat.major}</h3>
                <p className="metric">{stat.averageScore.toFixed(1)}</p>
                <p className="meta">Average Score</p>
                <p className="submeta">{stat.totalStudents} students</p>
              </article>
            ))}
          </div>
        </div>

        <div className="panel">
          <div className="panel-header">
            <h2>Student Snapshot</h2>
            <span>{students.length} records</span>
          </div>
          <div className="student-list">
            {students.map((student) => (
              <article key={student.studentId} className="student-row">
                <div>
                  <p className="student-name">{student.name}</p>
                  <p className="student-meta">
                    {student.studentId} · {student.major}
                  </p>
                </div>
                <div className={`score-pill ${student.score < 60 ? "risk" : ""}`}>
                  {student.score.toFixed(1)}
                </div>
              </article>
            ))}
          </div>
        </div>
      </section>
    </main>
  );
}

function SummaryCard({ label, value }: { label: string; value: string }) {
  return (
    <article className="summary-card">
      <p>{label}</p>
      <strong>{value}</strong>
    </article>
  );
}
