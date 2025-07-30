# Docker Counter

간단한 카운터 웹 애플리케이션입니다. +/- 버튼을 통해 숫자를 증가/감소시킬 수 있으며, 양수일 때는 '+' 기호가, 음수일 때는 '-' 기호가 자동으로 표시됩니다.

## 기술 스택

- **Backend**: Java 17, Spring Boot 3.2.0
- **Frontend**: Thymeleaf, HTML5, CSS3
- **Container**: Docker
- **CI/CD**: GitHub Actions
- **Cloud**: AWS EC2, Amazon ECR

## 프로젝트 구조

```
docker-counter/
├── src/
│   ├── main/
│   │   ├── java/com/example/dockercounter/
│   │   │   ├── DockerCounterApplication.java
│   │   │   └── CounterController.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── templates/
│   │           └── index.html
│   └── test/
├── .github/workflows/
│   └── deploy.yml
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

## 로컬 실행 방법

### 1. Maven으로 실행
```bash
# 의존성 설치
mvn clean install

# 애플리케이션 실행
mvn spring-boot:run
```

### 2. Docker로 실행
```bash
# Docker 이미지 빌드 및 실행
docker-compose up --build

# 또는 직접 Docker 명령어 사용
docker build -t docker-counter .
docker run -p 8080:8080 docker-counter
```

### 3. 접속
브라우저에서 `http://localhost:8080`으로 접속

## 배포 설정

### GitHub Secrets 설정

GitHub 저장소의 Settings > Secrets and variables > Actions에서 다음 시크릿을 설정해야 합니다:

- `AWS_ACCESS_KEY_ID`: AWS 액세스 키 ID
- `AWS_SECRET_ACCESS_KEY`: AWS 시크릿 액세스 키
- `AWS_REGION`: AWS 리전 (예: us-east-1)
- `EC2_HOST`: EC2 인스턴스의 퍼블릭 IP 또는 도메인
- `EC2_USERNAME`: EC2 인스턴스의 사용자명 (보통 ec2-user)
- `EC2_SSH_KEY`: EC2 인스턴스 접속용 SSH 프라이빗 키

### AWS 설정

1. **ECR 저장소 생성**:
   ```bash
   aws ecr create-repository --repository-name docker-counter
   ```

2. **EC2 인스턴스 설정**:
   - Amazon Linux 2 AMI 사용
   - 보안 그룹에서 포트 80, 22 열기
   - IAM 역할에 ECR 접근 권한 부여

3. **자동 배포**:
   - main 브랜치에 push하면 자동으로 배포됩니다
   - GitHub Actions가 Docker 이미지를 빌드하고 ECR에 푸시
   - EC2 인스턴스에서 최신 이미지를 다운로드하여 실행

## 주요 기능

- **카운터 증가/감소**: +/- 버튼으로 숫자 조작
- **자동 부호 표시**: 양수는 '+', 음수는 '-' 자동 표시
- **반응형 디자인**: 모바일과 데스크톱 모두 지원
- **자동 배포**: GitHub Actions를 통한 CI/CD 파이프라인

## 개발 정보

- **포트**: 8080
- **Java 버전**: 17
- **Spring Boot 버전**: 3.2.0
- **Docker 이미지**: Multi-stage build로 최적화

## 라이선스

MIT License 